import {User} from "./user.interface";

export interface Post {
    id: number,
    content: string,
    createdAt: string,
    updatedAt: string,
    article_id: number,
    user: User,
}
