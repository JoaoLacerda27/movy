import { Typography as MuiTypography } from '@mui/material';
import type { TypographyProps } from '@mui/material';

interface CustomTypographyProps extends TypographyProps {
  variant?: 'h1' | 'h2' | 'h3' | 'h4' | 'h5' | 'h6' | 'body1' | 'body2';
}

export function Typography({ children, variant = 'body1', ...props }: CustomTypographyProps) {
  const getStyles = () => {
    const baseStyles = {
      color: 'text.primary',
    };

    const variantStyles = {
      h1: {
        mb: 2,
        fontSize: '2.5rem',
        fontWeight: 600,
      },
      h2: {
        mb: 2,
        fontSize: '2rem',
        fontWeight: 600,
      },
      h3: {
        mb: 2,
        fontSize: '1.75rem',
        fontWeight: 600,
      },
      h4: {
        mb: 2,
        fontSize: '1.5rem',
        fontWeight: 600,
      },
      h5: {
        mb: 2,
        fontSize: '1.25rem',
        fontWeight: 600,
      },
      h6: {
        mb: 2,
        fontSize: '1rem',
        fontWeight: 600,
      },
      body1: {
        fontSize: '1rem',
      },
      body2: {
        fontSize: '0.875rem',
      },
    };

    return {
      ...baseStyles,
      ...variantStyles[variant],
      ...props.sx,
    };
  };

  return (
    <MuiTypography
      variant={variant}
      sx={getStyles()}
      {...props}
    >
      {children}
    </MuiTypography>
  );
} 