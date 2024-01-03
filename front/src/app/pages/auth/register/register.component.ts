import { Component, OnInit } from '@angular/core';
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

  constructor(private authService: AuthService, private router: Router, private matSnackBar: MatSnackBar) { }

  ngOnInit(): void {}

    register() {
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
}
