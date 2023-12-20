import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Topic} from "../interfaces/topic.interface";
import {User} from "../interfaces/user.interface";

@Injectable({
    providedIn: 'root'
})
export class TopicService {

    private pathService = 'topics'

    constructor(private httpClient: HttpClient) {}


    public getTopicById(id: Topic | undefined): Observable<Topic> {
        return this.httpClient.get<Topic>(`${environment.api}topic/${id}`);
    }

    public getTopics(): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${environment.api}${this.pathService}`);
    }
}
