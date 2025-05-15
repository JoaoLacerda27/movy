// Variantes e cores padrão para botões do projeto

import type { ButtonProps } from '@mui/material/Button';

export enum DefaultButtonEnum {
  CONTAINED = 'contained',
  OUTLINED = 'outlined',
  TEXT = 'text',
}

export const defaultButton = (
  type: DefaultButtonEnum,
  isSubmit: boolean,
  customWidth?: string,
  customHeight?: string
): ButtonProps => {
  const theme = {
    palette: {
      primary: {
        main: '#475569',
      },
    },
    breakpoints: {
      between: (start: string, end: string) => `@media (min-width: ${start}) and (max-width: ${end})`,
      down: (breakpoint: string) => `@media (max-width: ${breakpoint})`,
    },
  };

  return {
    type: isSubmit ? 'submit' : 'button',
    variant: type,
    sx: {
      minWidth: customWidth ? customWidth : '160px',
      height: customHeight ? customHeight : '50px',
      fontSize: '14px',
      fontWeight: '600',
      backgroundColor: theme.palette.primary.main,
      '&:hover': {
        backgroundColor: theme.palette.primary.main,
      },
      [theme.breakpoints.between('sm', 'md')]: {
        fontSize: '10px',
      },
      [theme.breakpoints.down('lg')]: {
        fontSize: '12px',
      },
    },
  };
};