import { Injectable } from '@angular/core';

const THEME_KEY = 'app-theme';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private darkClass = 'dark-mode';

  constructor() {
    this.loadTheme();
  }

  toggleTheme() {
    document.body.classList.toggle(this.darkClass);
    const isDark = document.body.classList.contains(this.darkClass);
    localStorage.setItem(THEME_KEY, isDark ? 'dark' : 'light');
  }

  loadTheme() {
    const theme = localStorage.getItem(THEME_KEY);
    if (theme === 'dark') {
      document.body.classList.add(this.darkClass);
    } else {
      document.body.classList.remove(this.darkClass);
    }
  }

  isDark(): boolean {
    return document.body.classList.contains(this.darkClass);
  }
}
