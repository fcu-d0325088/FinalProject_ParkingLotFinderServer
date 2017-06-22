package edu.fcu.tw.finalproject;


import java.util.ArrayList;

public class SkylineSearch 
{
	ArrayList<Path> pathList;
	public SkylineSearch(ArrayList<Path> pathList)
	{
		this.pathList=pathList;
	}
	ArrayList<Path> skylineSearch()
	{
		ArrayList<Path> resultList=new ArrayList<Path>();
		for(int i=0;i<pathList.size();i++)
		{
			boolean isSkylinePath=true;
			Path basePath=pathList.get(i);
			for(int j=0;j<pathList.size();j++)
			{
				Path tempPath=pathList.get(j);
				boolean dominated[]=new boolean[tempPath.feature.size()];
				for(int k=0;k<tempPath.feature.size();k++)
				{
					if(k!=2)
					{
						if(basePath.feature.get(k)>tempPath.feature.get(k))
						{
							dominated[k]=true;
						}
					}
					else
					{
						if(basePath.feature.get(k)<tempPath.feature.get(k))
						{
							dominated[k]=true;
						}						
					}
				}
				boolean isDominated=true;
				for(int k=0;k<tempPath.feature.size();k++)
				{
					if(dominated[k]==false)
					{
						isDominated=false;
					}
				}
				if(isDominated==true)
				{
					isSkylinePath=false;
				}
			}
			if(isSkylinePath==true)
			{
				resultList.add(pathList.get(i));
			}
		}
		return resultList;	
	}
}
