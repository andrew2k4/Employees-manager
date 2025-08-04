import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from './features/auth/storage/user-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'Sommermetallwerkstatt';
  isEmployeeLoggedIn: boolean = UserStorageService.isEmployeeLoggedIn();
  isManagerLoggedIn: boolean = UserStorageService.isManagerLoggedIn();

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((event) => {
      this.isEmployeeLoggedIn = UserStorageService.isEmployeeLoggedIn();
      this.isManagerLoggedIn = UserStorageService.isManagerLoggedIn();
    });
  }

  logout() {
    UserStorageService.signOut();
    this.router.navigateByUrl('login');
  }
}
