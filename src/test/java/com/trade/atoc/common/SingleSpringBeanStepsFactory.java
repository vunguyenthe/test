package com.trade.atoc.common;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.AbstractStepsFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SingleSpringBeanStepsFactory extends AbstractStepsFactory {

    private final ApplicationContext context;
    private final String beanName;

    public SingleSpringBeanStepsFactory(final Configuration configuration, final ApplicationContext context, final String beanName) {
        super(configuration);
        this.context = context;
        this.beanName = beanName;
    }

    @Override
    protected List<Class<?>> stepsTypes() {
        final List<Class<?>> types = new ArrayList<Class<?>>();
        for (final String name : context.getBeanDefinitionNames()) {
            final Class<?> type = context.getType(name);
            // Only run the steps that defined in stories-mapping.xml
            if (isAllowed(type) && hasAnnotatedMethods(type) && beanName.equalsIgnoreCase(name)) {
                types.add(type);
            }
        }
        return types;
    }

    protected boolean isAllowed(final Class<?> type) {
        return type != null && !Modifier.isAbstract(type.getModifiers());
    }

    @Override
    public Object createInstanceOfType(final Class<?> type) {
        for (final String name : context.getBeanDefinitionNames()) {
            final Class<?> beanType = context.getType(name);
            if (type.equals(beanType)) {
                return context.getBean(name);
            }
        }

        throw new StepsInstanceNotFound(type, this);
    }
}
