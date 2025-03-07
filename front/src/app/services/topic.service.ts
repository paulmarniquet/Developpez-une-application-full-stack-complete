import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Topic} from "../interfaces/topic.interface";

@Injectable({
    providedIn: 'root'
})
export class TopicService {

    private pathService = 'topics'

    constructor(private httpClient: HttpClient) {}

    public getTopics(): Observable<Topic[]> {
        return this.httpClient.get<Topic[]>(`${environment.api}${this.pathService}`);
    }
}
