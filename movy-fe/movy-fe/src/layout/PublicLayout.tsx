import { Box, Container } from '@mui/material';
import { Outlet } from 'react-router-dom';

export function PublicLayout() {
  return (
    <Box sx={{ 
      minHeight: '100vh',
      maxWidth: '100vw',
      backgroundColor: 'background.default',
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      justifyContent: 'center',
      padding: 0,
      margin: 0,
      '& .MuiContainer-root': {
        paddingLeft: '0 !important',
        paddingRight: '0 !important',
      }
    }}>
      <Container 
        maxWidth={false} 
        sx={{ 
          width: '100%',
          padding: 0,
          margin: 0,
          paddingLeft: '0 !important',
          paddingRight: '0 !important',
        }}
      >
        <Outlet />
      </Container>
    </Box>
  );
} 