from random import randrange

class DataGenerator:
    def __init__(self):
        pass
    
    @staticmethod
    def generateData(ids: list[str], callback:function):
        for id in ids:
            callback({id: randrange(150, 180)})
        pass