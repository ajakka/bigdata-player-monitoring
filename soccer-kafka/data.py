from faker import Faker

fake=Faker()
def get_data():
    return {
        "name":fake.name(),
        "address":fake.address(),
    }


print(get_data())