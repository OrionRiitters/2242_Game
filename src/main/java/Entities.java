import java.util.ArrayList;

public class Entities {

    Game game;
    ImageLoader imageLoader;

    public ArrayList<Vessel> vesselList = new ArrayList<Vessel>(); // Lists to contain all entities
    public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();


    public Entities(Game game) {
        this.game = game;
        imageLoader = game.imageLoader;
    }

    public PlayerVessel getPlayerVessel() {
        return (PlayerVessel) vesselList.get(0);
    }


    protected void addVesselToList(Vessel v) {
        vesselList.add(v);
    }

    protected void addProjectileToList(Projectile p) {
        projectileList.add(p);
    }

    protected void purgeProjectiles() {

        ArrayList<Projectile> projectileListBuffer = new ArrayList<Projectile>();
        ArrayList<Projectile> projectileListCopy = new ArrayList<Projectile>(projectileList); // Used for projectileList.removeAll() function later.

        for (Projectile p : projectileList) {
            if (!((p.getMaxX() < 0 || p.getMinX() > game.gui.FRAME_WIDTH) || (p.getMaxY() < 0 || // If out of bounds,
                    p.getMinY() > game.gui.FRAME_HEIGHT)) && p.getActive()) { // or if inactive,

                p.setProjectileIndex(projectileListBuffer.size());     // reset projectile's index,
                projectileListBuffer.add(p);                          // add projectile to bufferList,
            }
        }
        projectileList.removeAll(projectileListCopy);              // remove contents of projectileList,
        projectileList.addAll(projectileListBuffer);        // add contents of bufferList to projectileList.
    }


    protected void purgeVessels() {  // This does the same thing as purgeProjectiles, but purges vessels and
                                    // only checks each vessels 'active' attribute, and not if it is OOB
        ArrayList<Vessel> vesselListBuffer = new ArrayList<Vessel>();
        ArrayList<Vessel> vesselListCopy = new ArrayList<Vessel>(vesselList);

        for (Vessel v : vesselList) {
            if (v.getActive()) {

                v.setVesselID(vesselListBuffer.size());
                vesselListBuffer.add(v);
            }
        }
        vesselList.removeAll(vesselListCopy);
        vesselList.addAll(vesselListBuffer);
    }


    protected void runRoutines() {  // Runs routines of all entities
        for (Vessel v : vesselList) {
            v.routine();
        }

        for (Projectile p : projectileList) {
            p.routine();
        }
    }


    protected void createEnemy1(int minX, int minY) {  // Creates anonymous subclass of vessel

        addVesselToList(new Vessel(minX, minY,2, 20,
                50, imageLoader.getImage("enemyIMG"), true, false, Movement.E) {

            //String direction = "right";
            int frameWidth = game.gui.FRAME_WIDTH;
            int frameHeight = game.gui.FRAME_HEIGHT;
            int frame = 0;


            @Override
            protected void routine() { // If ship is out of bounds, send it back in bounds

                boolean OOB = false;
                frame = frame <= 64 ? frame + 1 : 0;

                if (getMaxX() > frameWidth) {
                    Movement.moveW(this, getSpeed());
                    OOB = true;
                }
                else if (getMinX() < 0) {
                    Movement.moveE(this, getSpeed());
                    OOB = true;
                }
                if (getMaxY() > frameHeight) {
                    Movement.moveN(this, getSpeed());
                    OOB = true;
                }
                else if (getMinY() < 0) {
                    Movement.moveS(this, getSpeed());
                    OOB = true;
                }

                if (!OOB) {
                    Movement.move(this, this.getDirection());
                }

                if (frame == 0) {
                    initializeProjectile();
                }


            }

            @Override
            protected void initializeProjectile() {  // This creates two new projectiles and adds them to entities.projectilesList

                addProjectileToList(new Projectile(getMinX() + 14, getMaxY() - 5, 4,
                        25, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.N, false) {

                    @Override
                    public void routine() {
                        Movement.moveS(this, getSpeed());
                    }

                });
            }

        });

    }

    protected void createEnemy2(int minX, int minY) { // Creates anonymous subclass of vessel

        addVesselToList(new Vessel(minX, minY,2, 2,
                75, imageLoader.getImage("enemy2IMG"), true, false, Movement.E) {

            //String direction = "right";
            int frameWidth = game.gui.FRAME_WIDTH;
            int frameHeight = game.gui.FRAME_HEIGHT;
            int frame = 0;


            @Override
            protected void routine() { // If ship is out of bounds, send it back in bounds

                boolean OOB = false;
                frame = frame <= 255 ? frame + 1 : 0;

                if (getMaxX() > frameWidth) {
                    Movement.moveW(this, getSpeed());
                    OOB = true;
                }
                else if (getMinX() < 0) {
                    Movement.moveE(this, getSpeed());
                    OOB = true;
                }
                if (getMaxY() > frameHeight) {
                    Movement.moveN(this, getSpeed());
                    OOB = true;
                }
                else if (getMinY() < 0) {
                    Movement.moveS(this, getSpeed());
                    OOB = true;
                }

                if (!OOB && frame != 256) {
                    Movement.move(this, this.getDirection());
                } else if (!OOB) {
                    Movement.move(this, Movement.randomDirection());
                }

                if (frame % 31 == 0) {
                    initializeProjectile();
                }


            }

            @Override
            protected void initializeProjectile() {  // This creates two new projectiles and adds them to entities.projectilesList

                addProjectileToList(new Projectile(getMinX() + 14, getMaxY() - 5, 5,
                        20, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.S, false) {

                    @Override
                    public void routine() {
                        Movement.moveS(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMinX() + 20, getMaxY() - 5, 5,
                        20, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.S, false) {

                    @Override
                    public void routine() {
                        Movement.moveS(this, getSpeed());
                    }

                });
            }



        });

    }

    protected void createEnemy3(int minX, int minY) { // Creates anonymous subclass of vessel

        addVesselToList(new Vessel(minX, minY,1, 10,
                75, imageLoader.getImage("enemy3IMG"), true, false, Movement.E) {

            //String direction = "right";
            int frameWidth = game.gui.FRAME_WIDTH;
            int frameHeight = game.gui.FRAME_HEIGHT;
            int frame = 0;


            @Override
            protected void routine() { // If ship is out of bounds, send it back in bounds

                boolean OOB = false;
                frame = frame <= 64 ? frame + 1 : 0;

                if (getMaxX() > frameWidth) {
                    Movement.moveW(this, getSpeed());
                    OOB = true;
                }
                else if (getMinX() < 0) {
                    Movement.moveE(this, getSpeed());
                    OOB = true;
                }
                if (getMaxY() > frameHeight) {
                    Movement.moveN(this, getSpeed());
                    OOB = true;
                }
                else if (getMinY() < 0) {
                    Movement.moveS(this, getSpeed());
                    OOB = true;
                }

                if (!OOB) {
                    Movement.move(this, this.getDirection());
                }

                if (frame == 0) {
                    initializeProjectile();
                }


            }

            @Override
            protected void initializeProjectile() {  // This creates two new projectiles and adds them to entities.projectilesList

                addProjectileToList(new Projectile(getMinX() + 10, getMinY(), 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.N, false) {

                    @Override
                    public void routine() {
                        Movement.moveN(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMaxX(), getMinY(), 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.NE, false) {

                    @Override
                    public void routine() {
                        Movement.moveNE(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMaxX(), getMinY() + 10, 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.E, false) {

                    @Override
                    public void routine() {
                        Movement.moveE(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMaxX(), getMaxY(), 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.SE, false) {

                    @Override
                    public void routine() {
                        Movement.moveSE(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMinX() + 10, getMaxY(), 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.S, false) {

                    @Override
                    public void routine() {
                        Movement.moveS(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMinX(), getMaxY(), 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.SW, false) {

                    @Override
                    public void routine() {
                        Movement.moveSW(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMinX(), getMinY() + 10, 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.W, false) {

                    @Override
                    public void routine() {
                        Movement.moveW(this, getSpeed());
                    }

                });

                addProjectileToList(new Projectile(getMinX(), getMinY(), 2,
                        30, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.NW, false) {

                    @Override
                    public void routine() {
                        Movement.moveNW(this, getSpeed());
                    }

                });
            }

        });

    }

    public void createBoss1(int minX, int minY) { // Creates anonymous subclass of vessel


        addVesselToList(new Vessel(minX, minY,1, 50,
                1000, imageLoader.getImage("boss1IMG"), true, false, Movement.E) {

            //String direction = "right";
            int frameWidth = game.gui.FRAME_WIDTH;
            int frameHeight = game.gui.FRAME_HEIGHT;
            int frame = 0;
            //boolean beamSwitch = true;


            @Override
            protected void routine() { // If ship is out of bounds, send it back in bounds

                boolean OOB = false;
                frame = frame <= 511 ? frame + 1 : 0;


                if (getMaxX() > frameWidth) {
                    Movement.moveW(this, getSpeed());
                    OOB = true;
                } else if (getMinX() < 0) {
                    Movement.moveE(this, getSpeed());
                    OOB = true;
                }
                if (getMaxY() > frameHeight) {
                    Movement.moveN(this, getSpeed());
                    OOB = true;
                } else if (getMinY() < 0) {
                    Movement.moveS(this, getSpeed());
                    OOB = true;
                }

                if (!OOB && frame != 128) {
                    Movement.move(this, this.getDirection());
                } else if (!OOB) {
                    Movement.move(this, Movement.randomDirection());
                }

                initializeProjectile();

            }

            @Override
            protected void initializeProjectile() {  // This creates two new projectiles and adds them to entities.projectilesList

                if (frame % 8 == 0 && frame < 255) {

                    addProjectileToList(new Projectile(getMinX(), getMaxY() - 5, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.S, false) {

                        @Override
                        public void routine() {
                            Movement.moveS(this, getSpeed());
                        }

                    });

                    addProjectileToList(new Projectile(getMinX() + 16, getMaxY() - 5, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.S, false) {

                        @Override
                        public void routine() {
                            Movement.moveS(this, getSpeed());
                        }

                    });

                    addProjectileToList(new Projectile(getMaxX() - 5, getMaxY() - 5, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.S, false) {

                        @Override
                        public void routine() {
                            Movement.moveS(this, getSpeed());
                        }

                    });

                    addProjectileToList(new Projectile(getMaxX() - 19, getMaxY() - 5, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.S, false) {

                        @Override
                        public void routine() {
                            Movement.moveS(this, getSpeed());
                        }

                    });

                }

                if (frame % 8 == 0 && frame > 320) {
                    addProjectileToList(new Projectile(getMaxX() - 20, getMaxY() - 46, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.E, false) {

                        @Override
                        public void routine() {
                            Movement.moveE(this, getSpeed());
                        }

                    });

                    addProjectileToList(new Projectile(getMaxX() - 20, getMaxY() - 57, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.E, false) {

                        @Override
                        public void routine() {
                            Movement.moveE(this, getSpeed());
                        }

                    });                    addProjectileToList(new Projectile(getMaxX() - 20, getMaxY() - 46, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.E, false) {

                        @Override
                        public void routine() {
                            Movement.moveE(this, getSpeed());
                        }

                    });

                    addProjectileToList(new Projectile(getMaxX() - 20, getMaxY() - 57, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.E, false) {

                        @Override
                        public void routine() {
                            Movement.moveE(this, getSpeed());
                        }

                    });

                    addProjectileToList(new Projectile(getMinX() + 18, getMaxY() - 46, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.W, false) {

                        @Override
                        public void routine() {
                            Movement.moveW(this, getSpeed());
                        }

                    });

                    addProjectileToList(new Projectile(getMinX() + 18, getMaxY() - 57, 5,
                            17, game.imageLoader.getImage("enemyProjectileIMG"), true,
                            getVesselID(), game, false, Movement.W, false) {

                        @Override
                        public void routine() {
                            Movement.moveW(this, getSpeed());
                        }

                    });
                }


                if ( 155 < frame && frame < 430) {

                    addProjectileToList(new Projectile(getMinX() + 36, getMaxY() - 38, 7,
                            1, game.imageLoader.getImage("bossProjectileIMG"), true,
                            getVesselID(), game, false, Movement.S, true) {

                        @Override
                        public void routine() {
                            Movement.moveS(this, getSpeed());
                        }



                    });

                }

            }
        });
    }


}