import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-manager',
  templateUrl: './signup-manager.component.html',
  styleUrl: './signup-manager.component.scss',
})
export class SignupManagerComponent {
  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.validateForm = this.fb.group(
      {
        email: [null, [Validators.email, Validators.required]],
        name: [null, [Validators.required]],
        address: [null, [Validators.required]],
        phone: [null],
        password: [null, [Validators.required]],
        passwordConfirmation: [null, [Validators.required]],
      },
      {
        validator: this.passwordMatchValidator,
      }
    );
  }

  passwordMatchValidator(
    formGroup: FormGroup
  ): { [key: string]: boolean } | null {
    if (
      formGroup.get('password')!.value !==
      formGroup.get('passwordConfirmation')!.value
    ) {
      return { mismatch: true };
    }
    return null;
  }

  submitForm() {
    if (this.validateForm.valid) {
      this.authService.registerCompany(this.validateForm.value).subscribe(
        (res) => {
          this.router.navigateByUrl('/login');
        },
        (error) => {
          console.error('Registration error', error);
          // Optionally, show an error message to the user
        }
      );
    } else {
      console.log('Form is not valid');
      // Optionally, show validation errors to the user
    }
  }
}
