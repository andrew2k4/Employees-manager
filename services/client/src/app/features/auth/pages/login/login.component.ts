import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UserStorageService } from '../../storage/user-storage.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'], // styleUrl -> styleUrls (bug corrigé)
  providers: [MessageService],
})
export class LoginComponent {
  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService,
    private userStorage: UserStorageService // ✅ injecte ton service ici
  ) {}

  ngOnInit() {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  submitForm() {
    this.authService
      .login(
        this.validateForm.get('username')?.value,
        this.validateForm.get('password')?.value
      )
      .subscribe(
        () => {
          // ✅ on utilise les méthodes d’instance, pas des statiques
          if (this.userStorage.isEmployee()) {
            this.router.navigateByUrl('/employee/dashboard');
          } else if (this.userStorage.isManager()) {
            this.router.navigateByUrl('/manager/dashboard');
          }
        },
        () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Anmeldung',
            detail: 'E-Mail oder Passwort ist falsch',
          });
        }
      );
  }
}
