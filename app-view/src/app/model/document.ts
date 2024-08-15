import {TypeDocument} from "./type-document";

export class Document {
  id!: number;
  description!:string;
  fileName!:string;
  typeDocument!:TypeDocument;
}
