import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from '../../../components/header/header.component';
import {AuthService} from "../../../services/auth.service";
import {LoginDto} from "../../../dto/login-dto.interface";
import {Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    user!: string;
    password!: string;

    constructor(private authService: AuthService, private router: Router) { }

    ngOnInit(): void {}

    login() {
        const request: LoginDto = {
            email: this.user,
            password: this.password
        }
        this.authService.login(request).subscribe((res: any) => {
            localStorage.setItem('token', res.token);
            this.router.navigate(['/articles']);
        });
    }

}
