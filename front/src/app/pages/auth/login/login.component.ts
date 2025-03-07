import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {LoginDto} from "../../../dto/login-dto.interface";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    user!: string;
    password!: string;

    constructor(private authService: AuthService, private router: Router, private matSnackBar: MatSnackBar) {
    }

    ngOnInit(): void {}

    login() {
        const request: LoginDto = {
            emailOrUsername: this.user,
            password: this.password
        };

        this.authService.login(request).subscribe(
            (res: any) => {
                localStorage.setItem('token', res.token);
                this.matSnackBar.open('You are logged in', 'Close', {
                    duration: 2000,
                });

                this.authService.me().subscribe(
                    (user: any) => {
                        localStorage.setItem('userID', JSON.stringify(user));
                        this.router.navigate(['/articles']);
                    },
                    (error: any) => {
                        console.error("Error fetching user details:", error.error);
                    }
                );
            },
            (error: any) => {
                console.error("Invalid credentials:", error.error);
                this.matSnackBar.open('Invalid credentials, please try again',
                    'Close', {
                        duration: 3000,
                        panelClass: ['redToast']
                    });
            }
        );
    }


}
