import type { UseFormRegisterReturn, FieldErrors } from 'react-hook-form';
import type { LoginFormInputs } from './interfaces';

export const getLoginFields = (
  register: (field: keyof LoginFormInputs) => UseFormRegisterReturn,
  errors: FieldErrors<LoginFormInputs>
) => ({
  email: {
    register: register('email'),
    label: 'Email',
    type: 'email',
    autoComplete: 'email',
    autoFocus: true,
    error: errors.email?.message,
  },
  password: {
    register: register('password'),
    label: 'Senha',
    type: 'password',
    autoComplete: 'current-password',
    error: errors.password?.message,
  },
}); 