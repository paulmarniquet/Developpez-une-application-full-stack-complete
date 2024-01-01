import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../services/profile.service";
import {User} from "../../interfaces/user.interface";
import {Topic} from "../../interfaces/topic.interface";
import {Router} from "@angular/router";

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    username: any;
    email: any;
    public user: User | undefined;
    public topics: Topic[] = [];
    private user_id: number = Number(localStorage.getItem('userID')!);

    constructor(private profileService: ProfileService, private router: Router) {
    }

    ngOnInit(): void {
        this.profileService.getProfile(this.user_id).subscribe((user: any) => {
            this.username = user.name
            this.email = user.email
            this.topics = user.topics
        });
    }

    save() {
        this.profileService.modifyProfile({
            name: this.username,
            email: this.email,
        }, this.user_id).subscribe(() => {
            this.router.navigate(['/articles']);
        });
    }

    logOut() {
        localStorage.removeItem('token');
        localStorage.removeItem('userID');
        this.router.navigate(['/login']);
    }
}
