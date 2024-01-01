import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {RegisterDto} from "../../../dto/register-dto.interface";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
    email!: string;
    password!: string;
    username!: string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {}

    register() {
        const request: RegisterDto = {
            email: this.email,
            password: this.password,
            name: this.username
        }
        this.authService.register(request).subscribe((res: any) => {
            localStorage.setItem('token', res.token);
            this.router.navigate(['/articles']);
        });

        this.authService.me().subscribe(
            (user: any) => {
                localStorage.setItem('userID', JSON.stringify(user));
            }
        );
    }
}
