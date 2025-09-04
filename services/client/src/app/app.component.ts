import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { UserStorageService } from './features/auth/storage/user-storage.service';
import { ThemeService } from './core/services/theme.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'Sommermetallwerkstatt';

  isLogged = false;
  isManager = false;
  isEmployee = false;
  isDark = false;
  constructor(
    private router: Router,
    private userStorage: UserStorageService,
    private themeService: ThemeService
  ) {}

  ngOnInit() {
    this.refreshUserState();
    this.isDark = this.themeService.isDark();
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.refreshUserState();
      }
    });
  }

  toggleTheme() {
    this.themeService.toggleTheme();
    this.isDark = this.themeService.isDark();
  }

  refreshUserState() {
    this.isLogged = this.userStorage.isLogged();
    this.isManager = this.userStorage.isManager();
    this.isEmployee = this.userStorage.isEmployee();

    console.log('État utilisateur :', {
      isLogged: this.isLogged,
      isManager: this.isManager,
      isEmployee: this.isEmployee,
      user: this.userStorage.getUser(),
    });
  }

  logout() {
    this.userStorage.clear();
    this.refreshUserState();
    this.router.navigateByUrl('/login');
  }
}
