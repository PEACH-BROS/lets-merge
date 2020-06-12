export default {
    namespaced: true,
    state: {
        missions: [
            {
                id: 1, name: "체스", isActive: "Y", startDateTime: "2020", dueDateTime: "2021", members: [
                    {id: 1, name: "김엘리", email: "elly@gmail.com", profile: "elly.png"},
                    {id: 2, name: "박비밥", email: "bebop@gmail.com", profile: "bebop.png"},
                ]
            },
            {
                id: 2, name: "블랙잭", isActive: "N", startDateTime: "2020", dueDateTime: "2021", members: [
                    {id: 1, name: "김엘리", email: "elly@gmail.com", profile: "elly.png"},
                    {id: 3, name: "탁코일", email: "coyle@gmail.com", profile: "coyle.png"},
                ]
            },
            {
                id: 3, name: "로또", isActive: "Y", startDateTime: "2020", dueDateTime: "2021", members: [
                    {id: 2, name: "박비밥", email: "bebop@gmail.com", profile: "bebop.png"},
                ]
            },
        ]
    }
}