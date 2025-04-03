```toml
name = 'Create Order'
method = 'POST'
url = 'http://localhost:8080/orders'
sortWeight = 1000000
id = 'aedf1eeb-f174-4e38-99cf-57e193e96b8e'

[body]
type = 'JSON'
raw = '''
{
  "userId": "0374c357-1b85-472f-a258-9def7f8bbe46",
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 2,
      "quantity": 1
    },
    {
      "productId": 3,
      "quantity": 3
    }
  ],
}'''
```
