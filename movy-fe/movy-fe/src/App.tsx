import { Routes, Route, Navigate } from 'react-router-dom';
import { ThemeProvider } from './contexts/ThemeContext';
import { MainLayout } from './layout/MainLayout';
import { PublicLayout } from './layout/PublicLayout';
import { PrivateRoute } from './components/logic-components';
import { Home } from './pages/Home';
import { About } from './pages/About';
import { Login } from './pages/Login/Login';

function App() {
  return (
    <ThemeProvider>
      <Routes>
        {/* Rotas Públicas */}
        <Route element={<PublicLayout />}>
          <Route path="/login" element={<Login />} />
        </Route>

        {/* Rotas Privadas */}
        <Route element={<PrivateRoute><MainLayout /></PrivateRoute>}>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
        </Route>

        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </ThemeProvider>
  );
}

export default App;
