INSERT INTO users (id, name, email, password, role, created_at)
VALUES (
           gen_random_uuid(),
           'Admin',
           'admin@movy.com',
           '$2a$10$9BkhOYPhN86/ME.rILolXug1S9aDnDdXbCpuxNzlYOCmL7LJ/5yKa', -- senha: admin123
           'ROLE_ADMIN',
           CURRENT_TIMESTAMP
       );