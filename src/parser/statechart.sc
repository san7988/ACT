SYSTEM : sys{
	
	STATE : s1{
		DESC : d1
		INPUT : i1,i2,i3
	}
	STATE : s2{
		DESC : d2
		INPUT : i4,i5
	} 
	
	TRANSITION : t1{
		SOURCE : s1
		DEST : s2
		GUARD : t=10
		ACTI : t=11
		  }
}
