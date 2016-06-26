Scenario: 
Given the DeviceId is <device_id>
When Mobile sends to TAS a message OrderSend with <content>
Then TAS sends to ATOC a message OrderSend with <content>
Then ATOC sends to MT4 a message OrderSend with <content>
Examples: 
|device_id|content|	approve_content |							
|ab-cd-de-ef-fe-ea| {"name":"OrderSend","content":{"magic":1,"symbol":"USDCAD","stoploss":100,"messageId":"7b4d599d-050a-4cca-b9de-c2e62803b4f3","takeprofit":120,"arrow_color":"BLUE","deviceId":"4b064ab1­4c51­4c50­8c68­07e027566117","volume":0.1,"message_status":"Approved","price":100,"comment":"","expiration":"","cmd":"OP_BUY","slippage":3}} |
