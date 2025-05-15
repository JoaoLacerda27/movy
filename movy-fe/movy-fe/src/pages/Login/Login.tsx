import { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { Box, Button, Typography, Paper, ThemeProvider } from '@mui/material';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { authService } from '../../services/auth';
import { defaultButton, DefaultButtonEnum } from '../../utils/buttonVariants';
import { lightTheme } from '../../theme';
import Logo from '../../assets/logo.png';
import { FormField } from './components/FormField';
import { getLoginFields } from './fields';
import { loginSchema } from './validation';
import type { LoginFormInputs } from './interfaces';

export function Login() {
  const navigate = useNavigate();
  const location = useLocation();
  const [error, setError] = useState('');

  const from = location.state?.from?.pathname || '/';

  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<LoginFormInputs>({
    resolver: yupResolver(loginSchema),
  });

  const onSubmit = async (data: LoginFormInputs) => {
    setError('');
    try {
      await authService.login(data);
      navigate(from, { replace: true });
    } catch {
      setError('Email ou senha inválidos');
    }
  };

  const fields = getLoginFields(register, errors);

  return (
    <ThemeProvider theme={lightTheme}>
      <Box
        sx={{
          display: 'flex',
          minHeight: '100vh',
          width: '100%',
          background: 'linear-gradient(135deg, #1E293B 60%, #475569 100%)',
        }}
      >
        <Box
          sx={{
            flex: 1,
            display: { xs: 'none', md: 'flex' },
            alignItems: 'center',
            justifyContent: 'center',
            backgroundColor: '#FFFFFF',
          }}
        >
          <Box
            sx={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              justifyContent: 'center',
              width: '100%',
            }}
          >
            <Box
              component="img"
              src={Logo}
              alt="Logo"
              sx={{
                width: { xs: 180, md: 240 },
                height: 'auto',
                mb: 2,
                filter: 'drop-shadow(0 2px 8px rgba(30,41,59,0.25))',
              }}
            />
          </Box>
        </Box>
        <Box
          sx={{
            flex: 1,
            width: '100%',
            height: '100vh',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            backgroundColor: 'transparent',
            border: 'none',
            outline: 'none',
            boxShadow: 'none',
            padding: 0,
            margin: 0,
          }}
        >
          <Paper
            elevation={0}
            sx={{
              padding: { xs: 3, sm: 4, md: 6 },
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              width: '100%',
              maxWidth: '400px',
              backgroundColor: '#FFFFFF',
              margin: 0,
            }}
          >
            <Typography 
              component="h1" 
              variant="h4" 
              sx={{ 
                mb: 4,
                color: '#1E293B',
                fontWeight: 600
              }}
            >
              Login
            </Typography>
            <Box component="form" onSubmit={handleSubmit(onSubmit)} sx={{ width: '100%' }}>
              <FormField {...fields.email} sx={{ mb: 2 }} />
              <FormField {...fields.password} sx={{ mb: 2 }} />
              {error && (
                <Typography color="error" sx={{ mb: 2 }}>
                  {error}
                </Typography>
              )}
              <Button
                {...defaultButton(DefaultButtonEnum.CONTAINED, true)}
                fullWidth
                size="large"
                sx={{
                  mt: 2,
                  py: 1.5,
                  fontSize: '1.1rem',
                }}
                disabled={isSubmitting}
              >
                Entrar
              </Button>
            </Box>
          </Paper>
        </Box>
      </Box>
    </ThemeProvider>
  );
} 