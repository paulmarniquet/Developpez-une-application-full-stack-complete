import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from '../../environments/environment';
import {Post} from "../interfaces/post.interface";
import {PostDto} from "../dto/post-dto.interface";

@Injectable({
    providedIn: 'root'
})
export class PostsService {

    private pathService : string = 'post'

    constructor(private httpClient: HttpClient) {}

    public getPostsOfArticle(id: number): Observable<Post[]> {
        return this.httpClient.get<Post[]>(`${environment.api}${this.pathService}/${id}`);
    }

    public createPost(post: PostDto): Observable<PostDto> {
        return this.httpClient.post<PostDto>(`${environment.api}${this.pathService}`, post);
    }
}
