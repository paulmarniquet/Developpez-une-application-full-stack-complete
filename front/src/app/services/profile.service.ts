import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { environment } from '../../environments/environment';
import {ProfileDto} from "../dto/profile-dto.interface";
import {Topic} from "../interfaces/topic.interface";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

    private pathService = 'user'

    constructor(private httpClient: HttpClient) {}

    public getProfile(userID: number) {
        return this.httpClient.get(`${environment.api}${this.pathService}/${userID}`);
    }

    public modifyProfile(profileDto: ProfileDto, userID: number) {
        return this.httpClient.put(`${environment.api}${this.pathService}/${userID}`, profileDto);
    }

    public subscribeTopic(topic: Topic, user_id: number) {
        return this.httpClient.put(`${environment.api}${user_id}/subscribe`, topic);
    }

    public unsubscribeTopic(topic: Topic, user_id: number) {
        return this.httpClient.put(`${environment.api}${user_id}/unsubscribe`, topic);
    }
}
