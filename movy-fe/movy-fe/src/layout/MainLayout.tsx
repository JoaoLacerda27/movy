import { Box, Container } from '@mui/material';
import { Navigation } from '../components/logic-components';
import { Outlet } from 'react-router-dom';

export function MainLayout() {
  return (
    <Box sx={{ 
      display: 'flex', 
      flexDirection: 'column', 
      minHeight: '100vh',
      backgroundColor: 'background.default'
    }}>
      <Navigation />
      <Container 
        component="main" 
        maxWidth="lg" 
        sx={{ 
          flex: 1, 
          py: 4,
          px: { xs: 2, sm: 3, md: 4 },
          width: '100%'
        }}
      >
        <Outlet />
      </Container>
    </Box>
  );
} 