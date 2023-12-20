import {Topic} from "./topic.interface";

export interface User {
    id: number,
    name: string,
    email: string,
    password: string,
    topics: Topic[]
}
