import { Injectable } from '@angular/core';

const TOKEN = 's_Token';
const USER = 's_ User';

@Injectable({
  providedIn: 'root',
})
export class UserStorageService {
  constructor() {}
  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  public saveUser(user: string): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getUSer(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  static getUserId(): string {
    const user = this.getUSer();
    if (user === null) {
      return '';
    }
    return user.userId;
  }

  static getUserRole(): string {
    const user = this.getUSer();
    if (user === null) {
      return '';
    }

    return user.role;
  }

  static isEmployeeLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === 'EMPLOYEE';
  }

  static isManagerLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role === 'MANAGER';
  }

  static signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
