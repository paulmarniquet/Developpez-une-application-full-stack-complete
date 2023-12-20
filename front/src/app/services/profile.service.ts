import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { environment } from '../../environments/environment';
import {ProfileDto} from "../dto/profile-dto.interface";
import {Topic} from "../interfaces/topic.interface";
import {User} from "../interfaces/user.interface";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

    private pathService = 'user'

    constructor(private httpClient: HttpClient) {}

    public getProfile() {
        return this.httpClient.get(`${environment.api}${this.pathService}/1`);
    }

    public modifyProfile(profileDto: ProfileDto) {
        return this.httpClient.put(`${environment.api}${this.pathService}/1`, profileDto);
    }

    public subscribeTopic(topic: Topic, user_id: number) {
        return this.httpClient.put(`${environment.api}${user_id}/subscribe`, topic);
    }

    public unsubscribeTopic(topic: Topic, user_id: number) {
        return this.httpClient.put(`${environment.api}${user_id}/unsubscribe`, topic);
    }
}
