```toml
name = 'Get Order Id'
method = 'GET'
url = 'http://localhost:8080/orders/2'
sortWeight = 3000000
id = '723c26c9-2f0f-436d-8214-443fa3829144'

[body]
type = 'JSON'
raw = '''
{
  "userId": "0374c357-1b85-472f-a258-9def7f8bbe46",
  "items": [
    {
      "productId": 1,
      "quantity": 1
    },
    {
      "productId": 2,
      "quantity": 1
    }
  ],
}'''
```
