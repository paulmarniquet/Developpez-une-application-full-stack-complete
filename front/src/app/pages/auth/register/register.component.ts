import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {RegisterDto} from "../../../dto/register-dto.interface";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
    email!: string;
    password!: string;
    username!: string;
    buttonClicked: boolean = false;
    infoEmail: boolean = false;
    infoPassword: boolean = false;

    constructor(private authService: AuthService, private router: Router, private matSnackBar: MatSnackBar) {
    }

    ngOnInit(): void {
    }

    register() {
        this.buttonClicked = true;
        !this.validateEmail(this.email) ? this.infoEmail = true : this.infoEmail = false;
        !this.validatePassword(this.password) ? this.infoPassword = true : this.infoPassword = false;

        const request: RegisterDto = {
            email: this.email,
            password: this.password,
            name: this.username
        }
        this.authService.register(request).subscribe((res: any) => {
                localStorage.setItem('token', res.token);
                this.authService.me().subscribe(
                    (user: any) => {
                        localStorage.setItem('userID', JSON.stringify(user));
                        this.router.navigate(['/articles']);
                    },
                    (error: any) => {
                        console.error("Error fetching user details:", error.error);
                    }
                );
                this.matSnackBar.open('You are registered', 'Close', {
                    duration: 2000,
                });
            },
            (error: any) => {
                console.error("Some fields are invalid:", error.error);
                this.matSnackBar.open('Some fields are invalid, please try again',
                    'Close', {
                        duration: 3000,
                        panelClass: ['redToast']
                    });
            }
        );
    }

    private validateEmail(email: string): boolean {
        const patternEmail = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        return patternEmail.test(email);
    }

    private validatePassword(password: string): boolean {
        const patternPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        return patternPassword.test(password);
    }
}
