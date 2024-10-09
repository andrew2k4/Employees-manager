import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupManagerComponent } from './basic/component/signup-manager/signup-manager.component';
import { LoginComponent } from './basic/component/login/login.component';
import { RegisterComponent } from './basic/component/register/register.component';
import { SignupEmployeeComponent } from './basic/component/signup-employee/signup-employee.component';

const routes: Routes = [
  { path: 'register-employee', component: SignupEmployeeComponent },
  { path: 'register-manager', component: SignupManagerComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'employee', loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule) },
  { path: 'manager', loadChildren: () => import('./manager/manager.module').then(m => m.ManagerModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
