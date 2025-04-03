```toml
name = 'create user'
method = 'POST'
url = 'http://localhost:8080/users'
sortWeight = 1000000
id = '3e7f1802-1132-494a-8c87-6ca674d478eb'

[body]
type = 'JSON'
raw = '''
{
  "fullName": "Jeferson Tavares",
  "address": "Rua: Mim morvam dias de figueiredo",
  "number": "7",
  "complement": ""
}'''
```

#### Pre-request Script

```js
jc.variables.get("variable_key")
```
