/* The key API_KEY here is a free key that is only good for 100 Api calls per month
If you get response:
{"success":false,"error":{"code":101,"type":"invalid_access_key","info":"You have not supplied a valid API Access Key. [Technical Support: support@apilayer.com]"}}
or
{"success":false,"error":{"code":104,"type":"usage_limit_reached","info":"Your monthly usage limit has been reached. Please upgrade your Subscription Plan."}}
You can sign up to get a new key from: https://coinlayer.com/?utm_source=apilayermarketplace&utm_medium=featured
You will then need to change the API_KEY with your new key
To test this API_KEY in a browser use: http://api.coinlayer.com/api/live?access_key=f62e882e811aeea67fda7dbcc26a64f5&target=USD
* */
package com.example.cryptoconverter.constants

const val END_POINT = "http://api.coinlayer.com/api/live?"
const val API_KEY = "f62e882e811aeea67fda7dbcc26a64f5"
const val DEFAULT_TARGET = "USD"