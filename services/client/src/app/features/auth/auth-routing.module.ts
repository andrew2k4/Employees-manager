import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { SignupManagerComponent } from './pages/signup-manager/signup-manager.component';
import { SignupEmployeeComponent } from './pages/signup-employee/signup-employee.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'register-employee', component: SignupEmployeeComponent },
  { path: 'register-manager', component: SignupManagerComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
