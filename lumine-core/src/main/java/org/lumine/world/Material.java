package org.lumine.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public enum Material {
    Air(0, "Air"), Stone(1, "Stone", "Clean stone"), Grass(2, "Grass"), Dirt(3,
            "Dirt", "Earth"), Cobblestone(4, "Cobblestone", "Cobble"), Wood(5,
            "Wood", "Wooden plank"), Sapling(6, "Sapling", "Seedling"), Bedrock(
            7, "Bedrock", "Adminium"), Water(8, "Water", "Flowing water"), StationaryWater(
            9, "Stationary water"), Lava(10, "Lava", "Flowing lava"), StationaryLava(
            11, "Stationary lava"), Sand(12, "Sand"), Gravel(13, "Gravel"), GoldOre(
            14, "Gold ore"), IronOre(15, "Iron ore"), CoalOre(16, "Coal ore"), Log(
            17, "Log", "Tree", "Branch"), Leaves(18, "Leaves", "Leaf"), Sponge(
            19, "Sponge"), Glass(20, "Glass"), Cloth(35, "Cloth", "Wool"), YellowFlower(
            37, "Flowe", "Yellow flower"), RedRose(38, "Rose", "Red rose"), BrownMushroom(
            39, "Mushroom", "Brown mushroom"), RedMushroom(40, "Toadstool",
            "Red mushroom"), GoldBlock(41, "Gold block", "Gold brick"), IronBlock(
            42, "Iron block", "Steel block", "Iron brick", "Steel brick"), DoubleStep(
            43, "Double step", "Double halfstep"), Step(44, "Step", "Halfstep"), Brick(
            45, "Brick"), TNT(46, "TNT", "Nitroglycerine", "TNT block",
            "Nitroglycerine block"), BookShelf(47, "Book shelf", "Bookshelf"), MossyCobblestone(
            48, "Mossy cobblestone", "Dungeon stone"), Obsidian(49, "Obsidian"), Torch(
            50, "Torch", "Candle"), Fire(51, "Fire"), MobSpawner(52,
            "Mob spawner", "Creature spawner", "Entity spawner", "Spawner"), WoodStairs(
            53, "Wood stairs"), Chest(54, "Chest", "Box"), RedstoneWire(55,
            "Redstone wire"), DiamondOre(56, "Diamond ore"), DiamondBlock(57,
            "Diamond block", "Diamond brick"), Workbench(58, "Workbench"), Crops(
            59, "Crops", "Wheat crops"), Soil(60, "Soil", "Mud"), Furnace(61,
            "Furnace", "Oven"), BurningFurnace(62, "Burning furnace",
            "Burning oven"), SignPost(63, "Sign post"), WoodenDoor(64,
            "Wooden door"), Ladder(65, "Ladder"), Rails(66, "Rails", "Tracks"), CobblestoneStairs(
            67, "Cobblestone stairs", "Cobble stairs", "Stone stairs"), WallSign(
            68, "Wall sign"), Lever(69, "Lever", "Switch"), StonePlate(70,
            "Stone plate"), IronDoorBlock(71, "Iron door block"), WoodPlate(72,
            "Wood plate"), RedstoneOre(73, "Redstone ore", "Redstone"), GlowingRedstoneOre(
            74, "Glowing Redstone ore", "Glowing Redstone"), RedstoneTorchOff(
            75, "Unlit Redstone torch", "Redstone torch off"), RedstoneTorchOn(
            76, "Lit Redstone torch", "Redstone torch on"), StoneButton(77,
            "Stone button", "Button"), Snow(78, "Snow"), Ice(79, "Ice"), SnowBlock(
            80, "Snow block"), Cactus(81, "Cactus", "Cactus block"), Clay(82,
            "Clay"), ReedBlock(83, "Sugar cane block", "Reed block",
            "Bamboo block", "Papyrus block"), Jukebox(84, "Jukebox",
            "Record player"), Fence(85, "Fence", "Fence post"), Pumpkin(86,
            "Pumpkin"), Netherstone(87, "Netherstone", "Netherrack",
            "Hellstone", "Bloodstone", "Red mossy cobblestone"), SlowSand(88,
            "Slow sand", "Soul sand", "Nether sand", "Hell mud", "Nethermud"), LightStone(
            89, "Light stone", "Lightstone", "Brittle gold", "Brightstone",
            "Australium", "Brimstone"), Portal(90, "Portal", "Portalstone"), JackOLantern(
            91, "Jack-O-Lantern", "Jackolantern"), IronSpade(256, "Iron spade"), IronPickaxe(
            257, "Iron pickaxe"), IronAxe(258, "Iron axe"), FlintAndSteel(259,
            "Flint and steel", "F&S"), Apple(260, "Apple"), Bow(261, "Bow"), Arrow(
            262, "Arrow"), Coal(263, "Coal"), Diamond(264, "Diamond"), IronIngot(
            265, "Iron ingot"), GoldIngot(266, "Gold ingot"), IronSword(267,
            "Iron sword"), WoodSword(268, "Wood sword", "Wooden sword"), WoodSpade(
            269, "Wood spade", "Wooden spade"), WoodPickaxe(270,
            "Wood pickaxe", "Wooden pickaxe", "Wooden pick", "Wood pick"), WoodAxe(
            271, "Wood axe", "Wooden axe"), StoneSword(272, "Stone sword"), StoneSpade(
            273, "Stone spade"), StonePickaxe(274, "Stone pickaxe",
            "Stone pick"), StoneAxe(275, "Stone axe"), DiamondSword(276,
            "Diamond sword"), DiamondSpade(277, "Diamond spade"), DiamondPickaxe(
            278, "Diamond pickaxe"), DiamondAxe(279, "Diamond axe"), Stick(280,
            "Stick"), Bowl(281, "Bowl", "Plate"), MushroomSoup(282,
            "Mushroom soup", "Soup"), GoldSword(283, "Gold sword",
            "Golden sword"), GoldSpade(284, "Gold spade", "Golden spade",
            "Gold shovel", "Golden shovel"), GoldPickaxe(285, "Gold pickaxe",
            "Golden pickaxe", "Golden pick", "Gold pick"), GoldAxe(286,
            "Gold axe", "Golden axe"), String(287, "Piece of string", "String"), Feather(
            288, "Feather"), Gunpowder(289, "Gunpowder"), WoodHoe(290,
            "Wood hoe", "Wooden hoe"), StoneHoe(291, "Stone hoe"), IronHoe(292,
            "Iron hoe"), DiamondHoe(293, "Diamond hoe"), GoldHoe(294,
            "Gold hoe", "Golden hoe"), Seeds(295, "Seeds", "Seed"), Wheat(296,
            "Wheat"), Bread(297, "Bread"), LeatherHelmet(298, "Leather helmet",
            "Leather hat"), LeatherChestplate(299, "Leather chestplate"), LeatherLeggings(
            300, "Leather leggings", "Leather trousers"), LeatherBoots(301,
            "Leather boots", "Leather shoes"), ChainmailHelmet(302,
            "Chainmail helmet"), ChainmailChestplate(303,
            "Chainmail chestplate"), ChainmailLeggings(304,
            "Chainmail leggings", "Chainmail trousers"), ChainmailBoots(305,
            "Chainmail boots", "Chainmail shoes"), IronHelmet(306,
            "Iron helmet"), IronChestplate(307, "Iron chestplate"), IronLeggings(
            308, "Iron leggings"), IronBoots(309, "Iron boots"), DiamondHelmet(
            310, "Diamond helmet"), DiamondChestplate(311, "Diamond chestplate"), DiamondLeggings(
            312, "Diamond leggings"), DiamondBoots(313, "Diamond boots"), GoldHelmet(
            314, "Gold helmet"), GoldChestplate(315, "Gold chestplate"), GoldLeggings(
            316, "Gold leggings"), GoldBoots(317, "Gold boots"), Flint(318,
            "Flint"), Pork(319, "Pork", "Ham", "Bacon"), GrilledPork(320,
            "Grilled pork", "Grilled ham", "Grilled bacon"), Painting(321,
            "Painting"), GoldenApple(322, "Golden apple"), Sign(323, "Sign"), WoodDoor(
            324, "Wooden door"), Bucket(325, "Bucket", "Iron bucket"), WaterBucket(
            326, "Water bucket", "Bucket of water"), LavaBucket(327,
            "Lava bucket", "Bucket of lava"), Minecart(328, "Minecart", "Cart"), Saddle(
            329, "Saddle", "Leather saddle"), IronDoor(330, "Iron door"), RedStone(
            331, "Redstone", "Redstone dust"), SnowBall(332, "Snow ball"), Boat(
            333, "Boat", "Ship"), Leather(334, "Leather", "Piece of leather"), MilkBucket(
            335, "Milk bucket"), ClayBrick(336, "Clay brick", "Clay block"), ClayBall(
            337, "Clay ball"), Reed(338, "Sugar cane", "Reed", "Bamboo",
            "Papyrus"), Paper(339, "Paper", "Sheet of paper"), Book(340, "Book"), SlimeBall(
            341, "Slime ball"), StorageMinecart(342, "Storage minecart",
            "Storage cart"), PoweredMinecart(343, "Powered minecart",
            "Powered cart"), Egg(344, "Egg", "Chicken egg"), Compass(345,
            "Compass"), FishingRod(346, "Fishing rod", "Fishing pole"), Watch(
            347, "Watch", "Clock"), LightstoneDust(348, "Light stone dust",
            "Lightstone dust", "Brittle gold dust", "Brightstone dust",
            "Australium dust", "Brimstone dust"), RawFish(349, "Raw fish",
            "Fish"), CookedFish(350, "Cooked fish", "Grilled fish"), InkSac(
            351, "Ink sac", "Ink"), Bone(352, "Bone"), Sugar(353, "Sugar"), Cake(
            354, "Cake"), GoldRecord(2256, "Gold record"), GreenRecord(2257,
            "Green record");

    private final int                                    id;
    private final ImmutableList<String>                  names;

    private static final ImmutableMap<Integer, Material> materialsForIds;
    static {
        final ImmutableMap.Builder<Integer, Material> mapBuilder = ImmutableMap
                .builder();
        for (final Material material : Material.values()) {
            mapBuilder.put(material.id, material);
        }
        materialsForIds = mapBuilder.build();
    }

    public static Material forId(final int id) {
        return Material.materialsForIds.get(id);
    }

    private Material(final int id, final String... names) {
        this.id = id;
        final ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (final String name : names) {
            builder.add(name);
        }
        this.names = builder.build();
    }

    public int id() {
        return this.id;
    }

    /**
     * Returns a list of names for this material, from most preferred to least
     * preferred. Names include spaces, match "[ a-zA-Z]+" and are otherwise
     * properly capitalized (the first letter of the name and proper names are
     * capitalized).
     */
    public ImmutableList<String> names() {
        return this.names;
    }
}
