```toml
name = 'List Order'
method = 'GET'
url = 'http://localhost:8080/orders'
sortWeight = 2000000
id = 'f92fbc9a-3d7f-43bf-acd5-56ef84981a8b'

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
