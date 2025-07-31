import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, Observer } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';

const Basic_Url = '/api/';
export const AUTH_HEADER = 'authorization';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private userStorageService: UserStorageService
  ) {}

  registerClient(signupRequestDTO: any): Observable<any> {
    return this.http.post(Basic_Url + 'employee/sign-up', signupRequestDTO);
  }

  registerCompany(signupRequestDTO: any): Observable<any> {
    return this.http.post(Basic_Url + 'manager/sign-up', signupRequestDTO);
  }

  login(username: string, password: string) {
    console.log(username, password);
    return this.http
      .post(
        Basic_Url + 'authenticate',
        { username, password },
        { observe: 'response' }
      )
      .pipe(
        map((res: HttpResponse<any>) => {
          console.log(res.body);
          this.userStorageService.saveUser(res.body);
          const tokenLength = res.headers.get(AUTH_HEADER)?.length;
          const bearerToken = res.headers
            .get(AUTH_HEADER)
            ?.substring(7, tokenLength);
          console.log(bearerToken);
          this.userStorageService.saveToken(bearerToken);
          return res;
        })
      );
  }
}
