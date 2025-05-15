import api from './api';

interface LoginCredentials {
  email: string;
  password: string;
}

interface AuthResponse {
  token: string;
  tokenType: string;
  name: string;
  email: string;
  role: string;
}

export const authService = {
  async login(credentials: LoginCredentials): Promise<AuthResponse> {
    const response = await api.post<AuthResponse>('v1/auth/login', credentials);
    const { token, tokenType } = response.data;
    
    localStorage.setItem('token', tokenType + ' ' + token);
    localStorage.setItem('name', response.data.name);
    localStorage.setItem('email', response.data.email);
    localStorage.setItem('role', response.data.role);
    return response.data;
  },

  async logout(): Promise<void> {
    localStorage.removeItem('token');
    await api.post('/auth/logout');
  },

  async getCurrentUser(): Promise<AuthResponse['name']> {
    const response = await api.get<AuthResponse>('/auth/me');
    return response.data.name;
  },

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  },
}; 