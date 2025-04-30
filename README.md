
## SETUP
Antes de tentar autenticar, rode este insert no banco de dados:

```SQL
INSERT INTO users ("name", "password", "role", status, username) VALUES('Administrador', '{bcrypt}$2a$10$8HNCkbG8m7T3lQYpAAoueeGGbMwJ1YLqAsVy2K4odHe4F9hfmZKOO', 1, 0, 'admin');
```

### As credenciais criadas são:
- **username:** admin
- **password:** 123
