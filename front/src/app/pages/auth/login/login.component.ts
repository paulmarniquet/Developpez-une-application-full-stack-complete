import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from '../../../components/header/header.component';
import {AuthService} from "../../../services/auth.service";
import {LoginDto} from "../../../dto/login-dto.interface";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    user: any;
    password: any;

    constructor(private authService: AuthService) { }


    ngOnInit(): void {
    }

    login() {
        const request: LoginDto = {
            email: this.user,
            password: this.password
        }
        this.authService.login(request).subscribe((res: any) => {
            console.log(res);
        });
    }

}
