import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root',
})
export class UserStorageService {
  private USER_KEY = 'user';
  private TOKEN_KEY = 'token';

  saveUser(user: any) {
    localStorage.setItem(this.USER_KEY, JSON.stringify(user));
  }

  getUser() {
    const user = localStorage.getItem(this.USER_KEY);
    return user ? JSON.parse(user) : null;
  }

  saveToken(token: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getUserId(): string | null {
    return this.getUser()?.userId || null;
  }

  isManager(): boolean {
    return this.getUser()?.role === 'MANAGER';
  }

  isEmployee(): boolean {
    return this.getUser()?.role === 'EMPLOYEE';
  }

  isLogged(): boolean {
    return !!this.getToken();
  }

  clear() {
    localStorage.clear();
  }
}
