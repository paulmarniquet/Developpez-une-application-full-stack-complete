import {User} from "./user.interface";
import {Topic} from "./topic.interface";

export interface Article {
    id: number,
    content: string,
    title: string,
    createdAt: string,
    user: User,
    topic: Topic,
}
