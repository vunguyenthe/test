Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: scenario description

Given the DeviceId is <device_id>
When Mobile sends to TAS a message OrderSend with <content>
Then TAS sends to ATOC a message OrderSend with <content>
Then ATOC sends to MT4 a message OrderSend with <content>
Examples:
|device_id|content|	approve_content |
|ab-cd-de-ef-fe-ea| {"name":"OrderSend","content":{"symbol":"USDCAD","cmd":"OP_BUY","volume":0.1,"price":100.0,"slippage":3,"stoploss":100.0,"takeprofit":120.0,"comment":"","magic":1,"expiration":"","arrow_color":"BLUE","message_status":"Unkown","messageId":"b8e5f8c8-6a45-4012-9abe-bd20fdfe5bc3","deviceAlias":"vunguyen","createdDate":1465661792898}} |