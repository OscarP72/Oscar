class Transaction {
    id: number = 0;
    current: number = 0;
    date: any;
    description: string = "";
    currentAccount: any;

    constructor(id: number, current: number, date: Date, description: string, currentAccount: string) {
        this.id = id;
        this.current = current;
        this.date = date;
        this.description = description;
        this.currentAccount = currentAccount;
    }
}
export default Transaction;