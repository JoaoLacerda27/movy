import { createTheme, type ThemeOptions } from '@mui/material/styles';

const commonThemeOptions: ThemeOptions = {
  typography: {
    fontFamily: '"Inter", "Helvetica", "Arial", sans-serif',
  },
  components: {
    MuiButton: {
      styleOverrides: {
        root: {
          textTransform: 'none',
        },
      },
    },
  },
};

export const lightTheme = createTheme({
  ...commonThemeOptions,
  palette: {
    mode: 'light',
    primary: {
      main: '#1E293B', // Azul petróleo - Cor principal, transmite confiança e tecnologia
    },
    secondary: {
      main: '#475569', // Azul acinzentado - Áreas secundárias e botões
    },
    info: {
      main: '#3B82F6', // Azul claro - Destaque, interações e status ativos
    },
    background: {
      default: '#F1F5F9', // Cinza claro - Fundo neutro
      paper: '#FFFFFF', // Branco - Cartões, áreas de conteúdo
    },
    success: {
      main: '#10B981', // Verde esmeralda - Sucesso
    },
    error: {
      main: '#EF4444', // Vermelho coral - Erro, falha, alerta de status
    },
  },
  components: {
    ...commonThemeOptions.components,
    MuiAppBar: {
      styleOverrides: {
        root: {
          backgroundColor: '#1E293B', // Azul petróleo para o header
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        ...commonThemeOptions.components?.MuiButton?.styleOverrides,
        contained: {
          backgroundColor: '#475569', // Azul acinzentado para botões
          color: '#fff',
          '&:hover': {
            backgroundColor: '#1E293B', // Azul petróleo para hover
          },
        },
        outlined: {
          borderColor: '#475569',
          color: '#475569',
          '&:hover': {
            backgroundColor: '#F1F5F9',
            borderColor: '#1E293B',
            color: '#1E293B',
          },
        },
      },
    },
    MuiCard: {
      styleOverrides: {
        root: {
          backgroundColor: '#FFFFFF',
          boxShadow: '0 1px 3px 0 rgb(0 0 0 / 0.1), 0 1px 2px -1px rgb(0 0 0 / 0.1)',
        },
      },
    },
  },
});

export const darkTheme = createTheme({
  ...commonThemeOptions,
  palette: {
    mode: 'dark',
    primary: {
      main: '#60A5FA',
    },
    secondary: {
      main: '#94A3B8',
    },
    info: {
      main: '#3B82F6',
    },
    background: {
      default: '#0F172A',
      paper: '#1E293B',
    },
    success: {
      main: '#34D399',
    },
    error: {
      main: '#F87171',
    },
  },
  components: {
    ...commonThemeOptions.components,
    MuiAppBar: {
      styleOverrides: {
        root: {
          backgroundColor: '#1E293B',
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        ...commonThemeOptions.components?.MuiButton?.styleOverrides,
        contained: {
          backgroundColor: '#475569',
          color: '#fff',
          '&:hover': {
            backgroundColor: '#334155',
          },
        },
        outlined: {
          borderColor: '#94A3B8',
          color: '#94A3B8',
          '&:hover': {
            backgroundColor: '#1E293B',
            borderColor: '#60A5FA',
            color: '#60A5FA',
          },
        },
      },
    },
    MuiCard: {
      styleOverrides: {
        root: {
          backgroundColor: '#1E293B',
          boxShadow: '0 1px 3px 0 rgb(0 0 0 / 0.3), 0 1px 2px -1px rgb(0 0 0 / 0.3)',
        },
      },
    },
  },
}); 