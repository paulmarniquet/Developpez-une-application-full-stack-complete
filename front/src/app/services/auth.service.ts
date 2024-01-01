import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {RegisterDto} from "../dto/register-dto.interface";
import {LoginDto} from "../dto/login-dto.interface";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private pathService = 'auth'

    constructor(private httpClient: HttpClient) {
    }

    public register(request: RegisterDto) {
        return this.httpClient.post(`${environment.api}${this.pathService}/register`, request);
    }

    public login(request: LoginDto) {
        return this.httpClient.post(`${environment.api}${this.pathService}/login`, request);
    }

    public me() {
        return this.httpClient.get(`${environment.api}${this.pathService}/me`);
    }
}
