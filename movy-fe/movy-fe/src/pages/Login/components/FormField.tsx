import { TextField } from '@mui/material';
import type { TextFieldProps } from '@mui/material';
import type { UseFormRegisterReturn } from 'react-hook-form';

interface FormFieldProps extends Omit<TextFieldProps, 'error' | 'helperText'> {
  register: UseFormRegisterReturn;
  error?: string;
  helperText?: string;
}

export const FormField = ({
  register,
  error,
  helperText,
  ...props
}: FormFieldProps) => {
  return (
    <TextField
      fullWidth
      {...register}
      error={!!error}
      helperText={error || helperText}
      sx={{
        '& .MuiInputLabel-root': {
          color: '#475569',
        },
        '& .MuiOutlinedInput-root': {
          '& fieldset': {
            borderColor: '#CBD5E1',
          },
          '&:hover fieldset': {
            borderColor: '#475569',
          },
          '&.Mui-focused fieldset': {
            borderColor: '#1E293B',
          },
        },
        '& .MuiInputBase-input': {
          color: '#1E293B',
        },
        '& .MuiFormHelperText-root': {
          color: error ? '#EF4444' : '#64748B',
        },
      }}
      {...props}
    />
  );
}; 