import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

    private pathService = 'auth'


    constructor(private httpClient: HttpClient) {
    }

    public getProfile() {
        return this.httpClient.get(`${environment.api}${this.pathService}/1`);
    }
}
