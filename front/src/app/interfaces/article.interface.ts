import {User} from "./user.interface";

export interface Article {
    id: number,
    content: string,
    title: string,
    createdAt: string,
    user: User,
    topic_id: number,
}
